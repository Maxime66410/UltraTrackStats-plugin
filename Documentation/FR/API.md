# API Publique

UltraTrackStats expose une API Java publique via la classe `StatsAPI`. N'importe quel plugin tournant sur le même serveur peut l'utiliser pour lire ou écrire des statistiques joueurs.

## Obtenir l'instance de l'API

```java
StatsAPI stats = StatsAPI.get();
```

Cette méthode statique retourne l'instance initialisée par UltraTrackStats. Elle peut être appelée à tout moment après l'activation d'UltraTrackStats.

> Il est recommandé d'ajouter `depend: [UltraTrackStats]` ou `softdepend: [UltraTrackStats]` dans votre `plugin.yml` pour garantir l'ordre de chargement.

---

## Méthodes

### `getStat`

Retourne la valeur actuelle d'une stat pour un joueur. Retourne `0` si la stat n'a jamais été définie.

```java
int getStat(Player player, String statId)
int getStat(UUID uuid, String statId)
```

**Exemple :**
```java
int kills = StatsAPI.get().getStat(player, "ZOMBIE");
```

---

### `addStat`

Incrémente une stat du montant donné. Crée l'entrée si elle n'existe pas encore.

```java
void addStat(Player player, String statId, int amount)
void addStat(UUID uuid, String statId, int amount)
```

**Exemple :**
```java
StatsAPI.get().addStat(player, "POINTS_QUETE", 10);
```

---

### `setStat`

Définit une stat à une valeur exacte. Crée l'entrée si elle n'existe pas encore.

```java
void setStat(Player player, String statId, int amount)
void setStat(UUID uuid, String statId, int amount)
```

**Exemple :**
```java
StatsAPI.get().setStat(player, "NIVEAU", 5);
```

---

### `resetStat`

Réinitialise une stat à `0`.

```java
void resetStat(Player player, String statId)
void resetStat(UUID uuid, String statId)
```

**Exemple :**
```java
StatsAPI.get().resetStat(player, "MORTS");
```

---

## IDs de stats

Un ID de stat est n'importe quelle `String` non nulle de 64 caractères maximum. Vous êtes libre de définir les vôtres. Les listeners intégrés utilisent les noms des enums Bukkit `Material` et `EntityType` comme IDs (ex. `"ZOMBIE"`, `"STONE"`).

**Conventions recommandées :**
- Intégrées : `"ZOMBIE"`, `"CREEPER"`, `"STONE"`, `"OAK_LOG"` ...
- Personnalisées : `"MONPLUGIN_POINTS_QUETE"`, `"MONPLUGIN_KILLS_DRAGON"` ...

Utiliser un préfixe de namespace (ex. `MONPLUGIN_`) évite les collisions avec d'autres plugins.

---

## Thread safety

Toutes les méthodes de l'API sont utilisables depuis n'importe quel thread. Quand une stat est modifiée, le `StatUpdateEvent` résultant est toujours déclenché sur le thread principal Bukkit, quel que soit le thread ayant appelé la méthode.

---

## Exemple complet — suivre des kills personnalisés

```java
@EventHandler
public void onEntityDeath(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof EnderDragon)) return;
    Player killer = event.getEntity().getKiller();
    if (killer == null) return;

    StatsAPI.get().addStat(killer, "KILLS_DRAGON", 1);

    int total = StatsAPI.get().getStat(killer, "KILLS_DRAGON");
    killer.sendMessage("Vous avez tué " + total + " dragon(s) !");
}
```
