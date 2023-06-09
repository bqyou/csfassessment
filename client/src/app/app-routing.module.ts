import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponent } from './components/search-review.component';
import { MovieReviewsListComponent } from './components/movie-reviews-list.component';
import { PostCommentComponent } from './components/post-comment.component';

const routes: Routes = [
  {path:'', component: SearchReviewComponent},
  {path:'searchMovie/:query', component: MovieReviewsListComponent},
  {path: 'commentMovie/:title', component: PostCommentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
