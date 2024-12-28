@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <a href="${NamedRoutes.buildPostPath()}">New Post</a>
    @for(var post : page.getPosts())
        <div>
            <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
        </div>
    @endfor
`)