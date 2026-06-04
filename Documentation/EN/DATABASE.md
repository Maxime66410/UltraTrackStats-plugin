# Database

## Overview

UltraTrackStats uses an embedded **SQLite** database stored in the plugin's data folder.

**File location:** `plugins/UltraTrackStats/stats.db`

The database is created automatically on first startup. No external database server is required.

---

## Schema

```sql
CREATE TABLE IF NOT EXISTS player_stats (
    uuid    VARCHAR(36),
    stat_id VARCHAR(64),
    amount  INTEGER DEFAULT 0,
    PRIMARY KEY (uuid, stat_id)
);
```

### Columns

| Column | Type | Description |
|---|---|---|
| `uuid` | `VARCHAR(36)` | Player UUID in string form (e.g. `"550e8400-e29b-41d4-a716-446655440000"`) |
| `stat_id` | `VARCHAR(64)` | Stat identifier string (see below) |
| `amount` | `INTEGER` | Current value of the stat, defaults to `0` |

The pair `(uuid, stat_id)` is the composite primary key — each player has one row per stat.

---

## Stat ID conventions

A stat ID is any string up to 64 characters. The built-in listeners use Bukkit enum names:

**Mob kills** — `EntityType` name:
```
ZOMBIE, SKELETON, CREEPER, SPIDER, ENDERMAN, BLAZE, WITHER_SKELETON ...
```

**Blocks broken / placed** — `Material` name:
```
STONE, DIRT, OAK_LOG, COAL_ORE, DEEPSLATE_DIAMOND_ORE, WHEAT ...
```

**Custom stats** (from other plugins) — free format, namespacing recommended:
```
MYPLUGIN_QUEST_POINTS, MYPLUGIN_DRAGON_KILLS ...
```

---

## SQL operations used

| Operation | SQL |
|---|---|
| Read | `SELECT amount FROM player_stats WHERE uuid = ? AND stat_id = ?` |
| Add | `INSERT ... ON CONFLICT DO UPDATE SET amount = amount + ?` |
| Set | `INSERT ... ON CONFLICT DO UPDATE SET amount = ?` |
| Reset | Same as Set with value `0` |

All queries use **prepared statements** to prevent SQL injection.
