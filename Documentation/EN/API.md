# Public API

UltraTrackStats exposes a public Java API through the `StatsAPI` class. Any plugin running on the same server can use it to read or write player statistics.

## Getting the API instance

```java
StatsAPI stats = StatsAPI.get();
```

This static method returns the API instance initialized by UltraTrackStats. It is safe to call at any time after UltraTrackStats has been enabled.

> It is recommended to add `depend: [UltraTrackStats]` or `softdepend: [UltraTrackStats]` in your `plugin.yml` to ensure load order.

---

## Methods

### `getStat`

Returns the current value of a stat for a player. Returns `0` if the stat has never been set.

```java
int getStat(Player player, String statId)
int getStat(UUID uuid, String statId)
```

**Example:**
```java
int kills = StatsAPI.get().getStat(player, "ZOMBIE");
```

---

### `addStat`

Increments a stat by the given amount. Creates the entry if it does not exist yet.

```java
void addStat(Player player, String statId, int amount)
void addStat(UUID uuid, String statId, int amount)
```

**Example:**
```java
StatsAPI.get().addStat(player, "CUSTOM_QUEST_POINTS", 10);
```

---

### `setStat`

Sets a stat to an exact value. Creates the entry if it does not exist yet.

```java
void setStat(Player player, String statId, int amount)
void setStat(UUID uuid, String statId, int amount)
```

**Example:**
```java
StatsAPI.get().setStat(player, "LEVEL", 5);
```

---

### `resetStat`

Resets a stat to `0`.

```java
void resetStat(Player player, String statId)
void resetStat(UUID uuid, String statId)
```

**Example:**
```java
StatsAPI.get().resetStat(player, "DEATHS");
```

---

## Stat IDs

A stat ID is any non-null `String` of up to 64 characters. You are free to define your own. The built-in listeners use Bukkit's `Material` and `EntityType` enum names as stat IDs (e.g. `"ZOMBIE"`, `"STONE"`).

**Recommended conventions:**
- Built-in: `"ZOMBIE"`, `"CREEPER"`, `"STONE"`, `"OAK_LOG"` ...
- Custom: `"MY_PLUGIN_QUEST_KILLS"`, `"MY_PLUGIN_POINTS"` ...

Using a namespace prefix (e.g. `MYPLUGIN_`) avoids collisions with other plugins.

---

## Thread safety

All API methods are safe to call from any thread. When a stat is modified, the resulting `StatUpdateEvent` is always fired on the Bukkit primary thread, regardless of which thread called the API method.

---

## Full example — tracking custom kills

```java
@EventHandler
public void onEntityDeath(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof Dragon)) return;
    Player killer = event.getEntity().getKiller();
    if (killer == null) return;

    StatsAPI.get().addStat(killer, "DRAGON_KILLS", 1);

    int total = StatsAPI.get().getStat(killer, "DRAGON_KILLS");
    killer.sendMessage("You have killed " + total + " dragon(s)!");
}
```
