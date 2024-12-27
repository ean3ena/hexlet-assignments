@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    <div>
        <form action="${NamedRoutes.loginPath()}" method="post">
            @if(page.getError() != null)
                <label>${page.getError()}</label>
            @endif
            <input type="text" placeholder="name" required name="name" value="${page.getName()}" />
            <input type="password" placeholder="password" required name="password" />
            <input type="submit" value="Войти"/>
        </form>
    </div>
`)