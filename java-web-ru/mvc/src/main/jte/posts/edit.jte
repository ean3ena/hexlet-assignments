@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="${NamedRoutes.postsPath()}" method="post">
    <div>
        <label>
            Name
            <input type="text" required name="name" value="${page.getName()}" />
        </label>
    </div>
    <div>
        <label>
            Body
            <input type="text" required name="body" value="${page.getBody()}" />
        </label>
    </div>
    <input type="submit" value="Сохранить" />
</form>