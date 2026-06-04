# Base de données

## Vue d'ensemble

UltraTrackStats utilise une base de données **SQLite** embarquée, stockée dans le dossier de données du plugin.

**Emplacement du fichier :** `plugins/UltraTrackStats/stats.db`

La base de données est créée automatiquement au premier démarrage. Aucun serveur de base de données externe n'est requis.

---

## Schéma

```sql
CREATE TABLE IF NOT EXISTS player_stats (
    uuid    VARCHAR(36),
    stat_id VARCHAR(64),
    amount  INTEGER DEFAULT 0,
    PRIMARY KEY (uuid, stat_id)
);
```

### Colonnes

| Colonne | Type | Description |
|---|---|---|
| `uuid` | `VARCHAR(36)` | UUID du joueur sous forme de chaîne (ex. `"550e8400-e29b-41d4-a716-446655440000"`) |
| `stat_id` | `VARCHAR(64)` | Identifiant de la stat (voir ci-dessous) |
| `amount` | `INTEGER` | Valeur actuelle de la stat, `0` par défaut |

La paire `(uuid, stat_id)` constitue la clé primaire composite — chaque joueur possède une ligne par stat.

---

## Conventions des IDs de stats

Un ID de stat est n'importe quelle chaîne de 64 caractères maximum. Les listeners intégrés utilisent les noms des enums Bukkit :

**Kills de mobs** — nom de l'`EntityType` :
```
ZOMBIE, SKELETON, CREEPER, SPIDER, ENDERMAN, BLAZE, WITHER_SKELETON ...
```

**Blocs cassés / posés** — nom du `Material` :
```
STONE, DIRT, OAK_LOG, COAL_ORE, DEEPSLATE_DIAMOND_ORE, WHEAT ...
```

**Stats personnalisées** (depuis d'autres plugins) — format libre, namespace recommandé :
```
MONPLUGIN_POINTS_QUETE, MONPLUGIN_KILLS_DRAGON ...
```

---

## Opérations SQL utilisées

| Opération | SQL |
|---|---|
| Lecture | `SELECT amount FROM player_stats WHERE uuid = ? AND stat_id = ?` |
| Ajout | `INSERT ... ON CONFLICT DO UPDATE SET amount = amount + ?` |
| Définition | `INSERT ... ON CONFLICT DO UPDATE SET amount = ?` |
| Réinitialisation | Identique à Définition avec la valeur `0` |

Toutes les requêtes utilisent des **prepared statements** pour prévenir les injections SQL.
