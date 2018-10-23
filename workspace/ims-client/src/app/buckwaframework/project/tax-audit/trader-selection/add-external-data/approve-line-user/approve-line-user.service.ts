import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class ApproveLineUserService {

    constructor(
        private ajax: AjaxService
    ) { }

    // ==> params

    // ==> functoin
    getCoordinatesArr = (): Promise<any> => {
        return new Promise((resolve, reject) => {
            const URL = "combobox/controller/getCoordinates";
            this.ajax.post(URL, {}, res => {
                resolve(res.json())
            });
        });
    }
    getSectorArr = (): Promise<any> => {
        return new Promise((resolve, reject) => {
            const URL2 = "combobox/controller/getDropByTypeAndParentId";
            this.ajax.post(URL2, { type: "SECTOR_VALUE" }, res => {
                resolve(res.json())
            });
        });
    }

}