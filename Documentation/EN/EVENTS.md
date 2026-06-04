# Events

## StatUpdateEvent

`StatUpdateEvent` is a custom Bukkit event fired every time a player stat is modified through `StatsAPI`. It allows other plugins to react to stat changes in real time.

**Class:** `org.FurranyStudio.UltraTrackStats.StatUpdateEvent`

---

## Fields

| Method | Return type | Description |
|---|---|---|
| `getPlayerUUID()` | `UUID` | UUID of the player whose stat changed |
| `getStatId()` | `String` | ID of the stat that was modified |
| `getNewValue()` | `int` | New value of the stat after the update |
| `getUpdateType()` | `UpdateType` | Type of update that occurred |

### UpdateType enum

| Value | Triggered by |
|---|---|
| `ADD` | `StatsAPI.addStat(...)` |
| `SET` | `StatsAPI.setStat(...)` |
| `RESET` | `StatsAPI.resetStat(...)` |

---

## Listening to the event

Add UltraTrackStats as a dependency in your `plugin.yml`:

```yaml
depend: [UltraTrackStats]
# or softdepend: [UltraTrackStats]
```

Then register a listener as usual:

```java
import org.FurranyStudio.UltraTrackStats.StatUpdateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyListener implements Listener {

    @EventHandler
    public void onStatUpdate(StatUpdateEvent event) {
        if (!event.getStatId().equals("ZOMBIE")) return;

        Player player = Bukkit.getPlayer(event.getPlayerUUID());
        if (player == null) return;

        player.sendMessage("Your zombie kill count is now: " + event.getNewValue());
    }
}
```

---

## Notes

- The event is always fired on the **primary Bukkit thread**, even when `StatsAPI` is called from an async thread.
- The event is **not cancellable** — it is informational only.
- `getNewValue()` always reflects the value **after** the update. For `RESET`, it will always be `0`.
