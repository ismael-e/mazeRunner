class Main {

    public static void main(String[] args)  {
        System.out.println("Hello World! Preparing to read input.txt");

        try {
            MazeImporter mazeImporter = new MazeImporter();
            Maze maze = mazeImporter.getMaze();
            maze.solve();

        } catch (Exception e) {
            System.out.println("An Error has occurred, please check the instructions and try again ");
        }

    }
}
