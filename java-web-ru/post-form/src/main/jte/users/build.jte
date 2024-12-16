@template.layout.page(content = @`
    <form action="/users" method="post">
        <div>
            <label>Имя</label>
            <input type="text" required name="firstName">
        </div>
        <div>
            <label>Фамилия</label>
            <input type="text" required name="lastName">
        </div>
        <div>
            <label>Email</label>
            <input type="email" required name="email">
        </div>
        <div>
            <label>Пароль</label>
            <input type="password" required name="password">
        </div>
        <input type="submit" value="Зарегистрировать">
    </form>
`)