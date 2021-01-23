import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IForm } from 'app/shared/model/form.model';

type EntityResponseType = HttpResponse<IForm>;
type EntityArrayResponseType = HttpResponse<IForm[]>;

@Injectable({ providedIn: 'root' })
export class FormService {
  public resourceUrl = SERVER_API_URL + 'api/forms';

  constructor(protected http: HttpClient) {}

  create(form: IForm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(form);
    return this.http
      .post<IForm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(form: IForm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(form);
    return this.http
      .put<IForm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IForm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IForm[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(form: IForm): IForm {
    const copy: IForm = Object.assign({}, form, {
      offerDate: form.offerDate && form.offerDate.isValid() ? form.offerDate.toJSON() : undefined,
      offerEndDate: form.offerEndDate && form.offerEndDate.isValid() ? form.offerEndDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.offerDate = res.body.offerDate ? moment(res.body.offerDate) : undefined;
      res.body.offerEndDate = res.body.offerEndDate ? moment(res.body.offerEndDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((form: IForm) => {
        form.offerDate = form.offerDate ? moment(form.offerDate) : undefined;
        form.offerEndDate = form.offerEndDate ? moment(form.offerEndDate) : undefined;
      });
    }
    return res;
  }
}
