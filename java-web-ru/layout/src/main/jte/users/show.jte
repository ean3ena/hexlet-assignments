@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <h1>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h1>
    <span>${page.getUser().getEmail()}</span>
`)


