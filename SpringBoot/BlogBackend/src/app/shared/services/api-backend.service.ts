import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { BehaviorSubject, Subject } from "rxjs";

const API_URL = environment.serviceUrl;
@Injectable({
  providedIn: "root"
})
export class ApiBackendService {
  constructor(
    private http: HttpClient

  ) { }


  query(param: object, urlName: string) {
    return this.http.post<any>(`${API_URL}/api_backend/${urlName}`, param)
      .pipe(map(data => { return data }));
  }

  save(param: object, urlName: string) {
    return this.http.post<any>(`${API_URL}/api_backend/${urlName}`, param)
      .pipe(map(data => { return data }))
  }

  upload(formData: object, urlName: string) {
    return this.http.post<any>(`${API_URL}/api_backend/${urlName}`, formData)
      .pipe(map(data => { return data }))
  }

  delete(param: object, urlName: string) {
    return this.http.post<any>(`${API_URL}/api_backend/${urlName}`, param)
      .pipe(map(data => { return data }))
  }

  downloadFile(url: string) {
    return `${API_URL}` + "/api_backend/imageFile" + url;
  }

}
