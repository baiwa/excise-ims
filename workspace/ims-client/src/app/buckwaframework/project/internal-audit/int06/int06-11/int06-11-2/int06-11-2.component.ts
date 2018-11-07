import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { FormBuilder } from '@angular/forms';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { TextDateTH, formatter } from 'helpers/datepicker';

declare var $: any;

@Component({
  selector: 'app-int06-11-2',
  templateUrl: './int06-11-2.component.html',
  styleUrls: ['./int06-11-2.component.css']
})
export class Int06112Component implements OnInit {

  @ViewChild('file') file;
  public files: Set<File> = new Set();

  medicalWelfareForm = this.fb.group({
    fullName: [''],
    gender: [''],
    birthDate: [''],
    siblingsOrder: [''],
    position: [''],
    affiliation: [''],
    phoneNumber: [''],
    status: [''],
    disease: [''],
    hospitalName: [''],
    hospitalOwner: [''],
    treatedDateFrom: [''],
    treatedDateTo: [''],
    totalMoney: [''],
    receiptQt: [''],
    claimStatus: [''],
    claimMoney: [''],
    ownerClaim: [''],
    otherClaim: [''],
    mateName: [''],
    mateCitizenId: [''],
    fatherName: [''],
    fatherCitizenId: [''],
    motherName: [''],
    motherCitizenId: [''],
    childName: [''],
    childCitizenId: [''],
    fileName: ['']
  });

  datatable: any;
  payLoad = '';

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private ajaxService: AjaxService,
    private message: MessageBarService
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06112');
    this.calenda();
  }

  calenda = () => {
    let date = new Date();
    $("#dateFrom").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter('day-month-year'),
      onChange: (date, text) => {
        this.medicalWelfareForm.get('treatedDateFrom').setValue(text);
      }
    });
    $("#dateTo").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter('day-month-year'),
      onChange: (date, text) => {
        this.medicalWelfareForm.get('treatedDateTo').setValue(text);
      }
    });
    $("#dateBirth").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter('day-month-year'),
      onChange: (date, text) => {
        this.medicalWelfareForm.get('birthDate').setValue(text);
      }
    });
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    for (let key in files) {
      if (!isNaN(parseInt(key))) {
        this.files.add(files[key]);
      }
    }
  }

  onSubmit() {
    console.warn(this.medicalWelfareForm.value);
    var url = "ia/int061102/save";
    this.ajaxService.post(url, JSON.stringify(this.medicalWelfareForm.value), res => {
      this.message.successModal('ทำรายการสำเร็จ');
    });
  }
}
