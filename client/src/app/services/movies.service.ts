import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Review } from '../models/Review';
import { MovieComment} from '../models/Comment';


@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  private searchMovieApiUrl = '/api/search'
  private postCommentApiUrl = '/api/comment'

  constructor(private http: HttpClient) { }

  getMovies(query: string){
    const params = new HttpParams()
                      .set('query', query)
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')
    return lastValueFrom(this.http.get<Review[]>(this.searchMovieApiUrl,{params: params, headers: headers}))
  }

  postComments(comment: MovieComment){
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = JSON.stringify(comment)
    return lastValueFrom(this.http.post<string>(this.postCommentApiUrl, body, {headers: headers}))


  }

  
}
