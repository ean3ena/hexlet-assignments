@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    <div>
        @if(page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
        @if(page.getName() != null)
            <h1>${page.getName().toString()}</h1>
            <form action="${NamedRoutes.logoutPath()}">
                <input type="submit" value="Выйти">
            </form>
        @endif
    </div>
`)