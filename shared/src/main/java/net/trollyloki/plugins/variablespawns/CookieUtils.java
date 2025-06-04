package net.trollyloki.plugins.variablespawns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class CookieUtils {
    private CookieUtils() {
    }

    private static final @NotNull Charset COOKIE_CHARSET = StandardCharsets.UTF_8;

    /**
     * Encodes a string into a byte array that can be stored as a cookie value.
     *
     * @param string string
     * @return byte array
     */
    public static byte @NotNull [] encodeString(@Nullable String string) {
        if (string == null) return new byte[0];
        else return string.getBytes(COOKIE_CHARSET);
    }

    /**
     * Decodes a string from a cookie value.
     *
     * @param value byte array
     * @return string
     */
    public static @Nullable String decodeString(byte @Nullable [] value) {
        if (value == null) return null;
        else return new String(value, COOKIE_CHARSET);
    }

}
