import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {AjaxService} from '../../../../../common/services/ajax.service';
import { Excise, ExciseTax, File } from '../../../../../common/models/index';
import { ExciseService, MessageBarService } from '../../../../../common/services/index';

@Component({
  selector: 'app-add-data',
  templateUrl: './add-data.component.html',
  styleUrls: ['./add-data.component.css']
})
export class AddDataComponent implements OnInit {

  id: string;
  excise: Excise;
  url: any;

  constructor(
    private route: ActivatedRoute,
    private _location: Location,
    private ajax: AjaxService,
    private ex: ExciseService,
    private messageBarService: MessageBarService
  ) {
    this.id = this.route.snapshot.queryParams['id'];
    this.excise = new Excise();
    this.excise.exciseTax = new Array<ExciseTax>();
    this.excise.file = new Array<File>();
    for(let i=0; i<3; i++) {
      this.excise.exciseTax.push(new ExciseTax());
      this.excise.file.push(new File());
    }
  }

  ngOnInit() {
    const url = `working/test/list/${this.id}/3`;
    if (this.ex.get(this.id) === null) {
      this.ajax
        .get(url, console.log(`GET '${url}'`), null)
        .then(
          res => {
            let ex = res.json()[0];
            this.excise = {
              change: ex.change,
              coordinates: ex.coordinates,
              createdBy: ex.createdBy,
              createdDatetime: ex.createdDatetime,
              exciseArea: ex.exciseArea,
              exciseFacAddress: ex.exciseFacAddress,
              exciseFacName: ex.exciseFacName,
              exciseId: ex.exciseId,
              exciseIdenNumber: ex.exciseIdenNumber,
              exciseOperatorName: ex.exciseOperatorName,
              exciseRegisCapital: ex.exciseRegisCapital,
              exciseRegisttionNumberId: ex.exciseRegisttionNumberId,
              exciseRemark: ex.exciseRemark,
              exciseTax: ex.exciseTax,
              file: this.excise.file,
              industrialAddress: ex.industrialAddress,
              no1: ex.no1,
              no2: ex.no2,
              no3: ex.no3,
              payingtax: ex.payingtax,
              paymentMonth: ex.paymentMonth,
              registeredCapital: ex.registeredCapital,
              sector: ex.sector,
              status: ex.status,
              taexciseProductType: ex.taexciseProductType,
              taexciseSectorArea: ex.taexciseSectorArea,
              taxpayment1: ex.taxpayment1,
              taxpayment2: ex.taxpayment2,
              updateBy: ex.updateBy,
              updateDatetime: ex.updateDatetime
            };
            this.ex.add(this.excise);
          }
        );
    } else {
      this.excise = this.ex.get(this.id);
    }
  }

  onUpdate(e): void {
    e.preventDefault();
    const url = `working/excise/list/${this.id}`;
    const data: File = {
      name: this.excise.file[0].name,
      type: this.excise.file[0].type,
      value: this.excise.file[0].value
    };
    this.ajax.put(url, data, null).then(
      res => {
        console.log(res);
      }
    );
    if (this.ex.update(this.excise)) {
      this.messageBarService.successModal('บันทึกข้อมูลเรียบร้อยแล้ว', 'สำเร็จ');
    } else {
      this.messageBarService.errorModal('ไม่สามารถบันทึกข้อมูลได้ กรุณาตรวจสอบการทำรายการ', 'เกิดข้อผิดพลาด');
    }
  }

  onCancel(): void {
    this._location.back();
  }

  readUrl(event:any, index: number) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (e:any) => {
        console.log(event.target.files[0]);
        const { name, type } = event.target.files[0];
        this.excise.file[index].name = name;
        this.excise.file[index].type = type;
        this.excise.file[index].value = e.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

}
