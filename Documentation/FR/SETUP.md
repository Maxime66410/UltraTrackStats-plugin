# Installation

## Prérequis

- Java 21 ou supérieur
- Spigot ou Paper 1.21.4
- Aucun plugin supplémentaire requis

## Installation

1. Téléchargez `UltraTrackStats-1.0-SNAPSHOT.jar`
2. Déposez-le dans le dossier `plugins/` de votre serveur
3. Démarrez ou redémarrez le serveur
4. Le plugin génère automatiquement son dossier de données dans `plugins/UltraTrackStats/` avec :
   - `config.yml` — fichier de configuration
   - `stats.db` — base de données SQLite (créée automatiquement)

## Configuration

Le fichier de configuration se trouve à `plugins/UltraTrackStats/config.yml`.

```yaml
listeners:
  enabled: false            # Interrupteur principal — doit être true pour activer les listeners intégrés
  track-block-break: true   # Suivre les blocs cassés par les joueurs
  track-block-place: true   # Suivre les blocs posés par les joueurs
  track-mob-kill: true      # Suivre les mobs tués par les joueurs
```

### Options

| Clé | Type | Défaut | Description |
|---|---|---|---|
| `listeners.enabled` | boolean | `false` | Interrupteur principal. Mettre à `true` pour activer les listeners intégrés. |
| `listeners.track-block-break` | boolean | `true` | Incrémente une stat à chaque bloc cassé par un joueur. L'ID de stat est le nom du matériau (ex. `STONE`). |
| `listeners.track-block-place` | boolean | `true` | Incrémente une stat à chaque bloc posé par un joueur. L'ID de stat est le nom du matériau (ex. `OAK_LOG`). |
| `listeners.track-mob-kill` | boolean | `true` | Incrémente une stat à chaque entité tuée par un joueur. L'ID de stat est le nom du type d'entité (ex. `ZOMBIE`). |

> **Note :** `listeners.enabled` doit être à `true` pour que les sous-options prennent effet, quelle que soit leur valeur individuelle.

### Comportement des listeners intégrés

**Listener de cassage de blocs :**
- Ignore les blocs posés par un joueur (marqués avec des métadonnées internes) pour éviter de récompenser les joueurs qui posent et recassent leurs propres blocs
- Ignore les cultures et plantes qui n'ont pas atteint leur maturité maximale (âge < âge maximum)

**Listener de pose de blocs :**
- Marque chaque bloc non-vieillissant posé par un joueur avec des métadonnées internes, pour que le listener de cassage puisse l'identifier plus tard

**Listener de kill de mobs :**
- Enregistre uniquement les kills dont la dernière source de dégâts est un joueur

## Rechargement

Pour appliquer les modifications de configuration sans redémarrer le serveur :

```
/uts reload
```

Voir [COMMANDS.md](COMMANDS.md) pour les détails de permission.
