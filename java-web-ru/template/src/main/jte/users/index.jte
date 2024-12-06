@import exercise.dto.users.UsersPage
@param UsersPage page

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Пользователи</title>
    </head>
    <body>
    @for(var user : page.getUsers())
        <div>
            <h2>
                <a href="/users/${user.getId()}">
                    ${user.getFirstName() + " " + user.getLastName()}
                </a>
            </h2>
        </div>
    @endfor
    </body>
</html>