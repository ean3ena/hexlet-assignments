@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(content = @`
    @for(var post : page.getPosts())
        <div>
            <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
        </div>
    @endfor
    @if(page.getPageNumber() > 1)
    <div>
        <a href="${NamedRoutes.postsPath(page.getPageNumber() - 1)}">Назад</a>
    </div>
    @endif
    <div>
        <a href="${NamedRoutes.postsPath(page.getPageNumber() + 1)}">Вперед</a>
    </div>
`)