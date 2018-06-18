import { Injectable } from '@angular/core';
import { TravelCostDetail, TravelCostHeader } from '../models';
import { Observable } from 'rxjs';

@Injectable()
export class TravelService {

  public header: TravelCostHeader;
  public detail: TravelCostDetail[];
  
  constructor() {
    this.detail = new Array<TravelCostDetail>();
  }

  details(): Observable<TravelCostDetail[]> {
    return new Observable<TravelCostDetail[]>((obs) => {
      obs.next(this.detail);
    });
  }

  filterDetails(what: string): Observable<TravelCostDetail[]> {
    return new Observable<TravelCostDetail[]>((obs) => {
      switch(what) {
        case 'checked': 
          obs.next(this.detail.filter( obj => { return !obj.checked }))
          break;
      }
    });
  }

}
