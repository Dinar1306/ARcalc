
/*

Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
Данные передаются в одну строку (смотрите пример)!
Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) цифрами.
Калькулятор умеет работать с цифрами от 1 до 10 включительно.
Калькулятор умеет работать только с целыми числами.
Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе строки вроде 3 + II калькулятор сообщит о проблеме и прекратит свою работу.
При вводе неподходящих чисел приложение сообщает о проблеме и завершает свою работу.
При вводе строки не соответствующей одной из вышеописанных арифметических операций калькулятор сообщит о проблеме и прекратит свою работу.
ПРИМЕР:
        Input:
        1 + 2

        Output:
        3

        Input:
        VI / III

        Output:
        II
*/

import java.io.*;




public class ARcalc {

    public static void main(String[] args) throws Exception {
        String stroka = null;       // вводимая строка
        String operation = null;   // введенная арифм.операция
        int a,b;                  // числа для операций из строки
        int resA = 0;            // число арабское после операций из строки
        Rdigits resR = null;    // число римское после операций из строки
        boolean isAArab, isBArab, isARim, isBRim;  // костыли

        // BEGIN OPERATIONS //
        // Error codes (win only)
        // https://docs.microsoft.com/ru-ru/windows/desktop/Debug/system-error-codes--0-499-


        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input:");
            stroka = reader.readLine(); // ввод строки с выражением

            if (stroka == null | stroka.equals("") | stroka.length() > 11) { // если пустая строка или больше 11 символов -> VIII + VIII
                System.out.println("Введена пустая строка или более 11 символов :(");
                System.exit(13);
            } else {    // иначе продолжаем проверки
                String[] d = stroka.split(" "); // делим строку по пробелам
                if (d.length != 3) {        // если после разбивки строки по пробелам полчилось не 3 элемента
                    System.out.println("Введена некорректная строка :(  ПРИМЕР: 1 + 2 или VI / III");
                    //throw new Exception();
                    System.exit(13);
                } else {   // иначе продолжаем проверки
                    if (!(d[1].equals("+") ||      //если второй символ не +, -, * или / то конец, иначе продолжаем
                            d[1].equals("-") ||
                                d[1].equals("*") ||
                                    d[1].equals("/"))) {
                        System.out.println("В выражении отсутствует корректный знак арифм.операции :(");
                        //throw new Exception();
                        System.exit(13);
                    } else {   // иначе продолжаем проверки
                        operation = d[1];  // зафиксировали арифм.операцию

                        // определяем наличие цифр и корректности ввода (в т.ч. две цифры в выражении, пример: 10 + 10)

                        //заводим костыли - определяем где арабские и где римские числа
                        isAArab = isArab(d[0]);
                        isBArab = isArab(d[2]);
                        isARim = isRome(d[0]);
                        isBRim = isRome(d[2]);

                        // проверка строки на числа вроде 3 + II
                        if ((isAArab | isBArab) & (isARim | isBRim)) {
                            System.out.println("В выражении поддерживаются только арабские или только римские числа ;)");
                            System.exit(13);
                        } else {
                            // если в строке только арабские числа
                            if (isAArab & isBArab) {
                                try {  // есть ли арабские цифры
                                    a = Integer.parseInt(d[0]);
                                    b = Integer.parseInt(d[2]);

                                    // всё хорошо, продолжаем с АРАБСКИМИ цифрами
                                    // проверяем цифры на вхождение в диапазон [1-10]
                                    if (!((a >= 1) & (a <= 10) & (b >= 1) & (b <= 10))) { // a и b в диапазоне [1-10] ?
                                        // если вне диапазона хотя бы одно из чисел, то конец
                                        System.out.println("Введены числа вне диапазона [1-10] :(");
                                        System.exit(84);
                                    } else { // если в диапазоне оба числа, то продолжаем -> проводим арифм.операцию
                                        switch (operation) {
                                            case "+":
                                                resA = (Arifmetika.plus(a, b));
                                                break;
                                            case "-":
                                                resA = (Arifmetika.minus(a, b));
                                                break;
                                            case "*":
                                                resA = (Arifmetika.umnog(a, b));
                                                break;
                                            case "/":
                                                try {
                                                    resA = (Arifmetika.delenie(a, b));
                                                } catch (MyCrashException e) {
                                                    e.printStackTrace();
                                                    System.exit(28);
                                                }
                                                break;
                                        } // switch  operation
                                        // проверка результата на вхождение в диапазон [1-10], т.к. калькулятор не знает других чисел
                                        if (!((resA >= 1) & (resA <= 10))) {
                                            System.out.println("Получен результат вне диапазона [1-10] :(");
                                            System.exit(84);
                                        } else { // если результат вхождит в диапазон [1-10], выводим на экран
                                            System.out.println();
                                            System.out.println("Output:");
                                            System.out.println(String.valueOf(resA)); // просто resA тоже выводится корректно
                                            System.out.println();
                                        }
                                    }
                                } catch (NumberFormatException e) { // не удалось получить число (исключительная ситуация, т.к. проверки уже пройдены (isAArab и isBArab)
                                    //e.getMessage();
                                    System.out.println("NumberFormatException ¯\\_(ツ)_/¯");
                                }
                            } // if (isAArab & isBArab)

                            else {
                                if (isARim & isBRim) {

                                    // если в строке только римские числа

                                    try {
                                        Rdigits aRim = Rdigits.valueOf(d[0]);
                                        Rdigits bRim = Rdigits.valueOf(d[2]);
                                        // всё хорошо, продолжаем с РИМСКИМИ цифрами

                                        // проверяем цифры на вхождение в диапазон [1-10]
                                        if (!((Rrifmetika.convert_R2A(aRim) >= 1) & (Rrifmetika.convert_R2A(aRim) <= 10) & (Rrifmetika.convert_R2A(bRim) >= 1) & (Rrifmetika.convert_R2A(bRim) <= 10))) { // a и b в диапазоне [1-10] ?
                                            // если вне диапазона хотя бы одно из чисел, то конец
                                            System.out.println("Введены числа вне диапазона [I-X] :(");
                                            System.exit(84);
                                        } else { // если в диапазоне оба числа, то продолжаем -> проводим арифм.операцию
                                            try {
                                                switch (operation) {
                                                    case "+":
                                                        resR = (Rrifmetika.plus(aRim, bRim));
                                                        break;
                                                    case "-":
                                                        resR = (Rrifmetika.minus(aRim, bRim));
                                                        break;
                                                    case "*":
                                                        resR = (Rrifmetika.umnog(aRim, bRim));
                                                        break;
                                                    case "/":
                                                        resR = (Rrifmetika.delenie(aRim, bRim));
                                                        break;
                                                } // switch  operation
                                            } catch (MyCrashException e) {
                                                e.printStackTrace();
                                                System.exit(28); // https://docs.microsoft.com/ru-ru/windows/desktop/Debug/system-error-codes--0-499-
                                            }
                                            // проверка результата на вхождение в диапазон [1-10], т.к. калькулятор не знает других чисел
                                            if (!((Rrifmetika.convert_R2A(resR) >= 1) & (Rrifmetika.convert_R2A(resR) <= 10))) {
                                                System.out.println("Получен результат вне диапазона [I-X] :(");
                                                System.exit(84);
                                            } else { // если результат вхождит в диапазон [1-10], выводим на экран
                                                System.out.println();
                                                System.out.println("Output:");
                                                System.out.println(String.valueOf(resR)); // просто resR тоже выводится корректно
                                                System.out.println();
                                            }
                                        }
                                    } catch (IllegalArgumentException ee) {  // не удалось получить римское число  - исключительная ситуация, т.к. проверки уже пройдены (isARim и isBRim)
                                        //ee.getMessage();
                                        System.out.println("IllegalArgumentException ¯\\_(ツ)_/¯");
                                    }
                                } //if (isARim & isBRim)
                                else {  // если не сработали условия: (арабских цифр), (римских цифр), (арабско-римских цифр)
                                    System.out.println("В выражении отсутствуют корректные числа :(");
                                    System.exit(11);
                                }
                            }


                            //////////////////
                            //    FINISH    //
                            //////////////////


                        }
                    }
                }
            }
            //reader.close();
        } // while true
    } // main

    //метод определения наличия арабского числа
    static boolean isArab (String chislo){
        boolean otvet = false;
        boolean temp = true;
        boolean [] chars = new boolean[chislo.length()];
        for (int i = 0; i < chislo.length(); i++) {
            chars[i] = Character.isDigit(chislo.charAt(i));
        }
        for (int j = 0; j < chars.length; j++) {
            temp = temp & chars[j];
        }
        otvet = temp;
        return otvet;
    } // static boolean isArab (String chislo)


    //метод определения наличия римского числа
    static boolean isRome (String chislo) {
        boolean otvet = false;
        Rdigits[] rdigits = Rdigits.values();
        for (int i = 0; i < rdigits.length; i++) {
            if (chislo.equals(rdigits[i].toString())) {
                otvet = true;
            }
        }
        return otvet;
    } // static boolean isRome
}
