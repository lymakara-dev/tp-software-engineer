import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class App {
    static boolean running = true;
    static String message = "HitMe!";
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GlobalScreen.registerNativeHook();

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent arg0){
                if(arg0.getKeyCode() == NativeKeyEvent.VC_ENTER){
                    try {
                        running = false;
                        GlobalScreen.unregisterNativeHook();
                    } catch (NativeHookException e) {
                        e.getStackTrace();
                    }
                }else if(arg0.getKeyCode() != NativeKeyEvent.VC_ENTER){
                    message = NativeKeyEvent.getKeyText(arg0.getKeyCode()).toLowerCase();
                }
            }
        });

        while(running){
            System.out.println(message);
            Thread.sleep(200);
        }

        System.out.println("Thank you!");
    }
}
