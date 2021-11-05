import java.io.*;
import java.util.Scanner;

public class BibCreator {

    public static void writeACMFile(String[] article_format, PrintWriter pwACM){
        String articleToWrite = article_format[0].split(" and ")[0] + " et al. "+article_format[3].trim() +'.'+" "+article_format[2].trim()+'.'+article_format[1].trim() +'.'+ article_format[4].trim()+ article_format[5].trim() + " (" + article_format[3].trim()+ "), " + article_format[6].trim() + ". DOI:https://doi.org/" + article_format[8].trim();
        pwACM.println(articleToWrite);
        pwACM.println();
    }
    public static void writeIEEEFile(String[] article_format, PrintWriter pwIEEE){
        String articleToWrite = article_format[0].trim()+'.'+' '+'"'+article_format[2].trim()+'"'+", "+article_format[1].trim()+", vol. "+article_format[4].trim()+", no. "+article_format[5].trim()+", p. "+article_format[6].trim()+", "+article_format[10].trim()+" "+article_format[3].trim()+'.';
        pwIEEE.println(articleToWrite);
        pwIEEE.println();
    }
    public static void writeNJFile(String[] article_format, PrintWriter pwNJ){
        String authors = "";
        int lengthOfAuthors = article_format[0].split("and").length;
        for(int i = 0; i<lengthOfAuthors; i++){
            if(i == lengthOfAuthors-1){
                authors = authors+article_format[0].split("and")[i];
            }else {
                authors = authors + article_format[0].split("and")[i];
                authors = authors + '&' + " ";
            }
        }
        String articleToWrite = authors.trim()+". "+article_format[2].trim()+'.'+" "+ article_format[1].trim()+'.'+" "+ article_format[4].trim()+','+article_format[6].trim()+'('+article_format[3].trim()+')'+".";
        pwNJ.println(articleToWrite.trim());
        pwNJ.println();
    }
    public static void processFilesForValidation(Scanner sc, int articleNumber, PrintWriter pw1, PrintWriter pw2, PrintWriter pw3) throws FileInvalidException {
        String article;
        String emptyBracket = "{}";
        String[] articleFields = new String[11];
        try {
            while(sc.hasNextLine()){
                sc.useDelimiter("@ARTICLE");
                while(sc.hasNext()) {
                    article=sc.next();
                    String[] article_lines = article.split("\n");
                    for (String article_line : article_lines) {
                        if (article_line.contains("author={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field author is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[0] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("journal={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field author is empty. Processing stopped at this point. Other empty fields maybe present as well!");

                            } else {
                                articleFields[1] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("title={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field title is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[2] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("year={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field year is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[3] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("volume={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field volume is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[4] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("number={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field number is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[5] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("pages={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field pages is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[6] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("keywords={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field keywords is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[7] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("doi={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field doi is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[8] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("ISSN={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field ISSN is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[9] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        } else if (article_line.contains("month={")) {
                            if (article_line.split("=")[1].contains(emptyBracket)) {
                                System.out.println("Error: Detected Empty Field! \n===============================\n");
                                throw new FileInvalidException("File is Invalid! Field month is empty. Processing stopped at this point. Other empty fields maybe present as well!");
                            } else {
                                articleFields[10] = article_line.split("=")[1].replace('{', ' ').replace('}', ' ').replace(',', ' ');
                            }
                        }
                    }
                    writeIEEEFile(articleFields, pw1);
                    writeACMFile(articleFields, pw2);
                    writeNJFile(articleFields, pw3);
                }
            }
        } catch (FileInvalidException e) {
            System.out.println("Problem detected with input file: Latex"+articleNumber+".bib");
            System.out.println(e.getMessage()+"\n");
        } finally {
            pw1.close();
            pw2.close();
            pw3.close();
        }
    }
    public static void readFromFile(BufferedReader bufferedReader) throws IOException {
        int charToRead;

        charToRead = bufferedReader.read();
        while(charToRead!=-1){
            System.out.print((char)charToRead);
            charToRead = bufferedReader.read();
        }
        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = null;
        PrintWriter pwIEEE = null;
        PrintWriter pwACM = null;
        PrintWriter pwNJ = null;
        String filename;
        BufferedReader br = null;
//        try {
//            openFiles();
//        } catch (FileInvalidException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            outputFiles();
//        } catch (FileInvalidException e) {
//            File dir = new File("Comp6481_F21_Assg2_Files");
//            for(File file: dir.listFiles())
//                if (!file.isDirectory())
//                    file.delete();
//        }
        System.out.println("Welcome to Bib Creator!\n");
        for (int i = 1; i <= 11; i++) {
            filename = "Comp6481_F21_Assg2_Files//Latex" + (i) + ".bib";
            try {
                sc = new Scanner(new FileInputStream(filename));
                pwIEEE = new PrintWriter(new FileOutputStream("Comp6481_F21_Assg2_Files/IEEE"+i+".json", true));
                pwACM = new PrintWriter(new FileOutputStream("Comp6481_F21_Assg2_Files/ACM"+i+".json", true));
                pwNJ = new PrintWriter(new FileOutputStream("Comp6481_F21_Assg2_Files/NJ"+i+".json", true));
                processFilesForValidation(sc, i, pwIEEE, pwACM, pwNJ);
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file Latex"+i+ ".bib for reading. \nPlease check if file exists! Program will terminate after closing any opened files.");
            }catch (FileInvalidException e1){
                System.out.println("Problem detected with input file: Latex"+i+".bib");
            }
        }

        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the name of one of the files that you need to review");
        String fileReview = userInput.next();
        try{
             br = new BufferedReader(new FileReader("Comp6481_F21_Assg2_Files\\"+fileReview));
             readFromFile(br);
        }catch(FileNotFoundException e){
            System.out.println("Could not open input file. File does not exist; possibly it could not be created!");
            System.out.println("\nHowever you will be allowed another chance to enter another filename");
            System.out.println("Please enter the name of one of the files that you want to review");
            fileReview = userInput.next();
            try {
                br = new BufferedReader(new FileReader("Comp6481_F21_Assg2_Files\\"+fileReview));
                readFromFile(br);
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Could not open input file. File does not exist; possibly it could not be created!");
                System.out.println("Sorry I am unable to display your desired files! Program will exit!");
                System.exit(1);
            }
        }
    }

}