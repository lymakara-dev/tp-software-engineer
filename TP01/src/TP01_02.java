import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class TP01_02 {
    static boolean isRunning = true;
    static String message = "HitMe!";

    public static void main(String[] args) throws Exception {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
                if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
                    isRunning = false;
                } else if (e.getKeyCode() != NativeKeyEvent.VC_ENTER) {
                    message = NativeKeyEvent.getKeyText(e.getKeyCode());
                }
            }
        });
        while (isRunning) {
            System.out.printf(message);
        }

        System.out.println("\n\nThank You!");
        GlobalScreen.unregisterNativeHook();

    }
}
