public class bullettrain {
    static final int width = getTerminalWidth() - 1;
    public static void main(String[] args) throws InterruptedException {
        String train = "╭――――――――――=╮"; //The ends look really boxy in monospace...

        while (true) {
        for (int i = 0; i < width; i++) {
        System.out.print("\r" + " ".repeat(i) + train);
        Thread.sleep(50);
        }
    }
    }
    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80;
        }
        }
        private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    
}
}

