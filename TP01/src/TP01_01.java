import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class TP01_01 {
    static boolean isRunning = true;

    public static void main(String[] args) throws Exception {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
                if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
                    isRunning = false;
                }
            }
        });
        while (isRunning) {
            System.out.printf("HitMe!");
        }

        System.out.println("\n\nThank You!");
        GlobalScreen.unregisterNativeHook();

    }
}
