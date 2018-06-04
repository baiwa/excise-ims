import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {AjaxService} from '../../../../../common/services/ajax.service';
import { Excise, ExciseTax } from '../../../../../common/models/index';
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
    private service: AjaxService,
    private ex: ExciseService,
    private messageBarService: MessageBarService
  ) {
    this.id = this.route.snapshot.queryParams['id'];
    this.excise = new Excise();
    this.excise.exciseTax = new Array<ExciseTax>();
    for(let i=0; i<3; i++) {
      this.excise.exciseTax.push(new ExciseTax());
    }
  }

  ngOnInit() {
    const url = `working/test/list/${this.id}/3`;
    if (this.ex.get(this.id) === null) {
      this.service
        .get(url, console.log(`GET '/working/test/list/${this.id}/3'`), null)
        .then(
          res => {
            this.excise = res.json()[0];
            this.ex.add(this.excise);
            console.log(this.excise);
          }
        );
    } else {
      this.excise = this.ex.get(this.id);
    }
  }

  onUpdate(e): void {
    e.preventDefault();
    if (this.ex.update(this.excise)) {
      this.messageBarService.successModal('บันทึกข้อมูลเรียบร้อยแล้ว', 'สำเร็จ');
    } else {
      this.messageBarService.errorModal('ไม่สามารถบันทึกข้อมูลได้ กรุณาตรวจสอบการทำรายการ', 'เกิดข้อผิดพลาด');
    }
  }

  onCancel(): void {
    this._location.back();
  }

  readUrl(event:any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event:any) => {
        this.url = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

}
