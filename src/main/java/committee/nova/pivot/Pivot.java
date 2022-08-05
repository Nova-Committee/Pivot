package committee.nova.pivot;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Pivot.MODID)
public class Pivot {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "pivot";

    public Pivot() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
