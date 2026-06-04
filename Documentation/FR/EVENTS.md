# Événements

## StatUpdateEvent

`StatUpdateEvent` est un événement Bukkit personnalisé déclenché à chaque modification d'une stat joueur via `StatsAPI`. Il permet à d'autres plugins de réagir aux changements de stats en temps réel.

**Classe :** `org.FurranyStudio.UltraTrackStats.StatUpdateEvent`

---

## Champs

| Méthode | Type retourné | Description |
|---|---|---|
| `getPlayerUUID()` | `UUID` | UUID du joueur dont la stat a changé |
| `getStatId()` | `String` | ID de la stat modifiée |
| `getNewValue()` | `int` | Nouvelle valeur de la stat après la mise à jour |
| `getUpdateType()` | `UpdateType` | Type de mise à jour effectuée |

### Enum UpdateType

| Valeur | Déclenché par |
|---|---|
| `ADD` | `StatsAPI.addStat(...)` |
| `SET` | `StatsAPI.setStat(...)` |
| `RESET` | `StatsAPI.resetStat(...)` |

---

## Écouter l'événement

Ajoutez UltraTrackStats comme dépendance dans votre `plugin.yml` :

```yaml
depend: [UltraTrackStats]
# ou softdepend: [UltraTrackStats]
```

Puis enregistrez un listener normalement :

```java
import org.FurranyStudio.UltraTrackStats.StatUpdateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MonListener implements Listener {

    @EventHandler
    public void onStatUpdate(StatUpdateEvent event) {
        if (!event.getStatId().equals("ZOMBIE")) return;

        Player player = Bukkit.getPlayer(event.getPlayerUUID());
        if (player == null) return;

        player.sendMessage("Votre compteur de zombies est maintenant : " + event.getNewValue());
    }
}
```

---

## Notes

- L'événement est toujours déclenché sur le **thread principal Bukkit**, même si `StatsAPI` a été appelé depuis un thread asynchrone.
- L'événement **n'est pas annulable** — il est purement informatif.
- `getNewValue()` reflète toujours la valeur **après** la mise à jour. Pour `RESET`, elle sera toujours `0`.
