@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(content = @`
    @if(page.getErrors() != null)
        <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
        </ul>
    @endif
    <form action="/articles" method="post">
        <div>
            <label>
                title
                <input type="text" name="title" value="${page.getTitle()}">
            </label>
        </div>
        <textarea type="text" name="content" value="${page.getContent()}">content</textarea>
        <input type="submit" value="Submit">
    </form>
`)
