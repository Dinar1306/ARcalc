public final class Rrifmetika {
    public static Rdigits plus (Rdigits a, Rdigits b) throws MyCrashException { return convert_A2R(convert_R2A(a) + convert_R2A(b)); }

    public static Rdigits minus (Rdigits a, Rdigits b) throws MyCrashException { return convert_A2R(convert_R2A(a) - convert_R2A(b));}

    public static Rdigits umnog (Rdigits a, Rdigits b) throws MyCrashException { return convert_A2R(convert_R2A(a) * convert_R2A(b)); }

    public static Rdigits delenie (Rdigits a, Rdigits b) throws MyCrashException {
        int res;
        if ((convert_R2A(a) % convert_R2A(b)) == 0) {
            res = convert_R2A(a) / convert_R2A(b);
        }
        else throw new MyCrashException("Невозможно отобразить результат: при делении " + a + " на " + b + " получено НЕ целое число!");
        return convert_A2R(res);
    }


    public static int convert_R2A(Rdigits rimDigit) throws MyCrashException {
        Integer arabDigitInt;
        int arabDigit;

        arabDigitInt = rimDigit.ordinal()+1;
        if (arabDigitInt == null) throw new MyCrashException("Проблема конвертации в арабское число: arabDigitInt = " + arabDigitInt);
        else arabDigit = arabDigitInt;

        return arabDigit;
    }

    public static Rdigits convert_A2R(int arabDigit) throws MyCrashException{
        Rdigits rimDigit = null;
        if ((arabDigit >= 1) & (arabDigit <= 10)) {
            switch (arabDigit) {
                case 1:
                    rimDigit = Rdigits.I;
                    break;
                case 2:
                    rimDigit = Rdigits.II;
                    break;
                case 3:
                    rimDigit = Rdigits.III;
                    break;
                case 4:
                    rimDigit = Rdigits.IV;
                    break;
                case 5:
                    rimDigit = Rdigits.V;
                    break;
                case 6:
                    rimDigit = Rdigits.VI;
                    break;
                case 7:
                    rimDigit = Rdigits.VII;
                    break;
                case 8:
                    rimDigit = Rdigits.VIII;
                   break;
                case 9:
                    rimDigit = Rdigits.IX;
                    break;
                case 10:
                    rimDigit = Rdigits.X;
                    break;
                }
        } else throw new MyCrashException("Результат вне диапазона доступных римских чисел [I-X]: " + arabDigit + " в арабской системе");
        if (rimDigit == null) throw new MyCrashException("Проблема конвертации в римское число: rimDigit = " + rimDigit);
        return rimDigit;
    }

}
