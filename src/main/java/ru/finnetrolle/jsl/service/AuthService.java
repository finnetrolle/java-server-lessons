package ru.finnetrolle.jsl.service;


import org.apache.commons.codec.digest.DigestUtils;
import ru.finnetrolle.jsl.model.User;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by finnetrolle on 19.12.2015.
 */
public class AuthService {

    private static volatile AuthService instance;

    public static AuthService getInstance() {
        AuthService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AuthService();
                }
            }
        }
        return localInstance;
    }

    private final Map<String, User> authorizedUsers = new ConcurrentHashMap<>();
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public boolean isAuthorized(String sessionId) {
        return authorizedUsers.containsKey(sessionId);
    }

    public User authorize(String sessionId, String login, String password) {
        if (isAuthorized(sessionId)) {
            return authorizedUsers.get(sessionId);
        }

        User user = users.get(login);
        if (user != null && user.getPasswordHash().equals(DigestUtils.sha256Hex(password))) {
            authorizedUsers.put(sessionId, user);
            return user;
        }

        return null;
    }

    public void unauthorize(String sessionId) {
        if (isAuthorized(sessionId)) {
            authorizedUsers.remove(sessionId);
        }
    }

    public User register(String login, String password) {
        if (users.containsKey(login)) {
            return null;
        }
        User user = new User(login, DigestUtils.sha256Hex(password));
        users.put(login, user);
        return user;
    }

    public void unregister(String name) {
        if (!users.containsKey(name)) {
            return;
        }
        authorizedUsers.entrySet().stream()
                .filter(es -> es.getValue().getLogin().equals(name))
                .map(es -> es.getKey())
                .forEach(k -> unauthorize(k));
        users.remove(name);
    }




}
