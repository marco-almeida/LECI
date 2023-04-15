package lab01;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GeneratorUtils {
    public static List<List<Character>> generatePuzzle(List<String> words, int puzzleSize){
        // Generate Random Puzzle Matrix as a bidimensional ArrayList of Characters
        List<List<Character>> puzzleCharacters = generateCharMatrix(puzzleSize);
        List<Coordinates> lockedCoordinates = new ArrayList<>();

        int numberOfWords = words.size();
        for(int i=0; i<numberOfWords; i++){
            String word = words.get(i);
            int wordLen = word.length();
            String direction = getDirection();

            List<Coordinates> wordCoordinates = getValidCoordinates(puzzleSize, wordLen, direction, lockedCoordinates);

            for(int j=0; j<wordCoordinates.size(); j++){
                lockedCoordinates.add(wordCoordinates.get(j));
            }

            for(int j=0; j<wordCoordinates.size(); j++){
                Coordinates newCoord = wordCoordinates.get(j);
                puzzleCharacters.get(newCoord.y).set(newCoord.x, word.charAt(j));
            }
        }
        return puzzleCharacters;
    }

    // Get Valid Coordinates for a word in puzzle
    public static List<Coordinates> getValidCoordinates(int puzzleSize, int wordLen, String direction, List<Coordinates> lockedCoordinates){
        List<Coordinates> validCoords = new ArrayList<>();
        boolean valid = false;
        Random rand = new Random();

        while(valid==false){
            validCoords.clear();
            int coordX = rand.nextInt(puzzleSize - wordLen);
            int coordY = rand.nextInt(puzzleSize - wordLen);

            if(direction=="horizontal"){
                for(int i=0; i<wordLen; i++){
                    coordX = coordX + 1;
                    Coordinates newCoordinate = new Coordinates(coordX, coordY);
                    validCoords.add(newCoordinate);
                }
            }
            if(direction=="vertical"){
                for(int i=0; i<wordLen; i++){
                    coordY = coordY + 1;
                    Coordinates newCoordinate = new Coordinates(coordX, coordY);
                    validCoords.add(newCoordinate);
                }
            }
            if(direction=="diagonal1"){
                for(int j=0; j<wordLen; j++){
                    coordX = coordX + 1;
                    coordY = coordY + 1;
                    Coordinates newCoordinate = new Coordinates(coordX, coordY);
                    validCoords.add(newCoordinate);
                }
            }
            if(direction=="diagonal2"){
                for(int j=0; j<wordLen; j++){
                    coordX = coordX - 1;
                    coordY = coordY - 1;
                    Coordinates newCoordinate = new Coordinates(coordX, coordY);
                    validCoords.add(newCoordinate);
                }
            }
            valid = validateCoords(validCoords,lockedCoordinates);
        } 
        return validCoords;
    }

    public static Boolean validateCoords(List<Coordinates> newCoords, List<Coordinates> lockedCoords){
        boolean valid = true;

        for(int i=0; i<newCoords.size(); i++){
            if(lockedCoords.contains(newCoords.get(i))){
                valid = false;
                return valid;
            }
            if(newCoords.get(i).x<0 || newCoords.get(i).y<0){
                valid = false;
                return valid;
            }
        }

        return valid;
    }

    // Randomly assign a direction
    public static String getDirection(){
        String direction = "null";
        int directionInt;
        Random rand = new Random();

        directionInt = rand.nextInt(4);
        switch(directionInt){
            case 0: 
                direction = "horizontal";
                break;
            case 1:
                direction = "vertical";
                break;
            case 2: 
                direction = "diagonal1";
                break;
            case 3:
                direction = "diagonal2";
                break;
        }
        return direction;
    }

    // generate a square matrix of random Characters
    public static List<List<Character>> generateCharMatrix(int size){
        List<List<Character>> charMatrix = new ArrayList<>();

        for(int i=0; i<size; i++){
            List<Character> line = new ArrayList<>();
            for(int j=0; j<size; j++){
                Random rand = new Random();
                char c = (char)(rand.nextInt(26) + 'A');
                line.add(c);
            }
            charMatrix.add(line);
        }
        return charMatrix;
    }

    public static void printPuzzle(List<List<Character>> puzzle){
        int puzzleSize = puzzle.size();

        for(int i=0; i<puzzleSize; i++){
            for(int j=0; j<puzzleSize; j++){
                System.out.print(puzzle.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
