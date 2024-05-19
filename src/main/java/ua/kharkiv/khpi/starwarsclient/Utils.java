package ua.kharkiv.khpi.starwarsclient;

public final class Utils {

    public static String extractIdFromUrl(String url) {
        // Находим индекс символа "/" после "people/"
        int index = url.indexOf("/people/") + "/people/".length();
        // Обрезаем строку, начиная с этого индекса
        String idString = url.substring(index);
        // Находим индекс следующего "/"
        int endIndex = idString.indexOf("/");
        // Если следующий "/" найден, обрезаем строку до него, иначе используем всю строку
        if (endIndex != -1) {
            idString = idString.substring(0, endIndex);
        }
        return idString;
    }
}
