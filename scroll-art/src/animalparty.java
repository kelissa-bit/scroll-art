import java.util.Random;

public class kuma {
    static final int width = getTerminalWidth() - 1;
    static final int kuma_width = 9;
    static final int kuma_height = 6;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[9][width];
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];
        while (true) {
    for (int x = 0; x < width - 34; x += 34) {
        if (isBlank(prevRow, x) && rand.nextDouble() < 0.08) {
            loadNextRowsWithImage(nextRows, x);
                }
            }
            System.out.println(new String(nextRows[0]));
        prevRow = nextRows[0];
        shiftRowsUp(nextRows);
        Thread.sleep(100);
        long time = System.currentTimeMillis() - startTime;
        iterations++;
        }
        }
        private static boolean isBlank(char[] prevRow, int x) {
            for (int i = x; i < x + 34; i++) {
                if (prevRow[i] != ' ') {
                    return false;
                }
            }
        return true;
    }
        private static void loadNextRowsWithImage(char[][] nextRows, int x) {
            String llama = """
                /^---^\\
                | . . |
                \\  `  /   MS
                /=====\\________
                /               \\[]
                \\               /
                ---------------
                | || |   | || |
                [|][|]   [|][|]
                    """;
                    AsciiArt l = new AsciiArt(llama);
                String penguin = """                   
              .--.
             |o_o |
             |/_/ |
            //   \\ \\
            (|     | )
            /'\\_   _/`\\
            \\___)=(___/ AH
            """;
            AsciiArt p = new AsciiArt(penguin);

            String bear = """
                      ⛧
                      ʌ
                    / \\
                    @___@
                    (-_-)
                    <--->
                    /| |\\/~~~~~~ AG
                    """;
                    AsciiArt b = new AsciiArt(bear);

                    AsciiArt[] images = {new AsciiArt(getkuma()), l, p, b};
                    AsciiArt art = images[rand.nextInt(images.length)];
                    AsciiArt widestArt = AsciiArt.getWidestArt(images);
                    

                    for (int iy = 0; iy < art.height; iy++) {
                        for (int ix = 0; ix < art.width; ix++) {
                            nextRows[iy][x+ix]=art.img[iy][ix];
                        }}
                        }

static void shiftRowsUp(char[][] nextRows) {
    for (int i = 1; i < nextRows.length; i++) {
        nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

static char[] emptyRow() {
        char[] row = new char[width];
        for (int i = 0; i < width; i++) {
            row[i] = ' ';
        }
        return row;
    }

    static char[][] getkuma() {
        char[][] img = new char[kuma_height][kuma_width];
        
        for (int y = 0; y < kuma_height; y++) {
            for (int x = 0; x < kuma_width; x++) {
                img[y][x] = ' ';
            }
        }
        img[0][0] = ' ';
        img[0][1] = ' ';
        img[0][2] = 'p';
        img[0][3] = ' ';
        img[0][4] = '◞';
        img[0][5] = '◟';
        img[0][6] = 'p';

        img[1][0] = ' ';
        img[1][1] = '(';
        img[1][2] = '˶';
        img[1][3] = '˃';
        img[1][4] = 'ᵕ';
        img[1][5] = '˂';
        img[1][6] = '˶';
        img[1][7] = ')';

        img[2][0] = '૮';
        img[2][1] = '(';
        img[2][2] = ' ';
        img[2][3] = ' ';
        img[2][4] = ' ';
        img[2][5] = ' ';
        img[2][6] = ' ';
        img[2][7] = ')';
        img[2][8] = 'ა';

        img[3][0] = ' ';
        img[3][1] = ' ';
        img[3][2] = ' ';
        img[3][3] = 'ᵕ';
        img[3][4] = ' ';
        img[3][5] = 'ᵕ';

        img[4][0] = ' ';
        img[4][1] = '\\';
        img[4][2] = ' ';
        img[4][3] = '|';
        img[4][4] = ' ';
        img[4][5] = '|';
        img[4][6] = ' ';
        img[4][7] = '/';

        img[5][0] = ' ';
        img[5][1] = '✶';
        img[5][2] = '⋆';
        img[5][3] = '˚';
        img[5][4] = '모';
        img[5][5] = '✧';
        img[5][6] = '˖';
        img[5][7] = '°';
        img[5][8] = '.';

        return img;
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
