/* TerminalIO.java: Utility class for doing curses-style menu-based terminal
 * programs using ANSI escape sequences.
 *
 * TO_DO: For screen width / height calculations to work, the $LINES and
 * $COLUMNS shell variables need to be exported. Look into how ncurses
 * gets this information.
 *
 * Author:   Heath Harrelson <harrel2@pdx.edu>
 * Created:  2009-11-24
 * Modified: 2013-02-18
 *
 */

class TerminalIO {
	private static final String ESC = "\033[";
	private static final String NORMAL = ESC + "0m";
	private static final String BOLD = ESC + "1m";
	private static final String CLEAR = ESC + "2J";
	private static final String CLEAR_LINE = ESC + "0K";
	private static final String SEEK_TOP = ESC + "1;1H";
	private static final String REVERSE_VIDEO = ESC + "7m";

	private static int WIDTH;
	private static int HEIGHT;

	private static int currentRow = 1;
	private static int currentCol = 1;

	static {
		try {
			HEIGHT = Integer.parseInt(System.getenv("LINES"));
			WIDTH  = Integer.parseInt(System.getenv("COLUMNS"));
		} catch (Exception e) {
			// Assume the screen is 40x80
			HEIGHT = 40;
			WIDTH  = 80;
		}
	}

	/* Returns the height of the window in lines */
	public static int screenHeight () {
		return HEIGHT;
	}

	/* Returns the width of the window in columns / characters */
	public static int screenWidth () {
		return WIDTH;
	}

	/* Force a value to be within a range. */
	private static int clampToRange (int value, int min, int max) {
		value = Math.max(value, min);
		value = Math.min(value, max);

		return value;
	}

	/* Adds highlighting escape sequences to a String so it will be bold. */
	public static String highlightString (String text) {
		return BOLD + text + NORMAL;
	}

	/* Pads string to line length. */
	public static String padStringToLineLength (String text) {
		String padding = "";

		if (text.length() < WIDTH) {
			for (int i = 0; i < WIDTH - text.length(); i++) {
				padding += " ";
			}
		}

		return text + padding;
	}

	/* Prints a string to the terminal using default text. */
	public static void printPlain (String text) {
		System.out.print(text);
		System.out.flush();
	}

    /* Prints a string to the terminal using bold text by using ANSI terminal
     * escape sequences. */
	public static void printHighlighted (String text) {
		System.out.print(highlightString(text));
		System.out.flush();
	}

	/* Prints text out with foreground and background colors swapped. */
	public static void printReverseVideo (String text) {
		System.out.print(REVERSE_VIDEO + text + NORMAL);
		System.out.flush();
	}

	/* Clears the screen and repositions the cursor at line 0, column 0 using 
	 * ANSI terminal escape sequences. */
	public static void clearScreen () {
		System.out.print(CLEAR);
		System.out.print(SEEK_TOP);
		System.out.flush();

		currentRow = 1;
		currentCol = 1;
	}

	/* Clears from the cursor position to the end of the line. */
	public static void clearLine () {
		System.out.print(CLEAR_LINE);
		System.out.flush();
	}

	/* Seeks to line n and column m of the screen. */
	public static void seekTo (int line, int column) {
		int seekLine = clampToRange(line, 1, HEIGHT);
		int seekCol  = clampToRange(column, 1, WIDTH);

		System.out.print(ESC + seekLine + ";" + seekCol + "H");

		currentRow = seekLine;
		currentCol = seekCol;
	}

	/* Seeks to line n of the screen. */
	public static void seekToLine (int line) {
		seekTo(line, currentCol);
	}

	/* Seeks to column n of the screen. */
	public static void seekToColumn (int column) {
		seekTo(currentRow, column);
	}
}
