package banking.common.infrastructure.security;

import banking.Translator;
import java.util.function.Function;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UpdatableBCrypt {

    private final int logRounds;

    public UpdatableBCrypt(int logRounds) {
        this.logRounds = logRounds;
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        if (BCrypt.checkpw(password, hash)) {
            int rounds = getRounds(hash);
            if (rounds != logRounds) {
                String newHash = hash(password);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }

    private int getRounds(String salt) {
        char minor = (char) 0;
        int off = 0;
        if (salt.charAt(0) != '$' || salt.charAt(1) != '2') {
            throw new IllegalArgumentException(Translator.toLocale("message.token.version"));
        }
        if (salt.charAt(2) == '$') {
            off = 3;
        } else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$') {
                throw new IllegalArgumentException(Translator.toLocale("message.token.version"));
            }
            off = 4;
        }
        if (salt.charAt(off + 2) > '$') {
            throw new IllegalArgumentException(Translator.toLocale("message.token.version"));
        }
        return Integer.parseInt(salt.substring(off, off + 2));
    }
}
