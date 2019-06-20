public final class Arifmetika {
    public static int plus (int a, int b){
        int res = a + b;
        return res;
    }

    public static int minus (int a, int b){
        int res = a - b;
        return res;
    }

    public static int umnog (int a, int b){
        int res = a * b;
        return res;
    }

    public static int delenie (int a, int b) throws MyCrashException {
        int res;
        if ((a % b) == 0) {
            res = a / b;
        }
        else throw new MyCrashException("Невозможно отобразить результат: при делении " + a + " на " + b + " получено НЕ целое число!");
        return res;
    }

}




