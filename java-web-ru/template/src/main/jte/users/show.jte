@import exercise.dto.users.UserPage
@param UserPage page

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Пользователь</title>
    </head>
    <body>
        <main>
            <h1>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</h1>
            <h2>Email: ${page.getUser().getEmail()}</h2>
        </main>
    </body>
</html>