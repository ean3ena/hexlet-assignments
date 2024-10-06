package exercise;

import lombok.Value;

// BEGIN
import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
