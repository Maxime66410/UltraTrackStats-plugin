# Setup

## Requirements

- Java 21 or higher
- Spigot or Paper 1.21.4
- No additional plugins required

## Installation

1. Download `UltraTrackStats-1.0-SNAPSHOT.jar`
2. Drop it into your server's `plugins/` folder
3. Start or restart your server
4. The plugin will generate its data folder at `plugins/UltraTrackStats/` with:
   - `config.yml` — configuration file
   - `stats.db` — SQLite database (created automatically)

## Configuration

The configuration file is located at `plugins/UltraTrackStats/config.yml`.

```yaml
listeners:
  enabled: false            # Master switch — must be true to enable any built-in listener
  track-block-break: true   # Track blocks broken by players
  track-block-place: true   # Track blocks placed by players
  track-mob-kill: true      # Track mobs killed by players
```

### Options

| Key | Type | Default | Description |
|---|---|---|---|
| `listeners.enabled` | boolean | `false` | Master toggle. Set to `true` to activate the built-in listeners. |
| `listeners.track-block-break` | boolean | `true` | When enabled, increments a stat each time a player breaks a block. The stat ID is the block's material name (e.g. `STONE`). |
| `listeners.track-block-place` | boolean | `true` | When enabled, increments a stat each time a player places a block. The stat ID is the block's material name (e.g. `OAK_LOG`). |
| `listeners.track-mob-kill` | boolean | `true` | When enabled, increments a stat each time a player kills an entity. The stat ID is the entity type name (e.g. `ZOMBIE`). |

> **Note:** `listeners.enabled` must be `true` for any of the sub-options to take effect, regardless of their individual values.

### Built-in listener behavior

**Block break listener:**
- Ignores blocks that were placed by a player (tagged with internal metadata) to avoid rewarding players for placing and re-breaking their own blocks
- Ignores crops and plants that have not fully matured (age < max age)

**Block place listener:**
- Tags every non-ageable block placed by a player with internal metadata so the break listener can identify it later

**Mob kill listener:**
- Only records kills where the entity's last damage cause was a player

## Reloading

To apply configuration changes without restarting the server, use the in-game command:

```
/uts reload
```

See [COMMANDS.md](COMMANDS.md) for permission details.
