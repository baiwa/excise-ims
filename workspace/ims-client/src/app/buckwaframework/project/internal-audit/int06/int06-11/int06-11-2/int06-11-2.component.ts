import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { FormBuilder } from '@angular/forms';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { Utils } from 'helpers/utils';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-int06-11-2',
  templateUrl: './int06-11-2.component.html',
  styleUrls: ['./int06-11-2.component.css']
})
export class Int06112Component implements OnInit {

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
    files: [],
    type: ['']
  });

  datatable: any;
  payLoad = '';
  flag: boolean = true;
  arrayUpload: any = [];
  fileArray: any = [];
  delFlag: boolean = false;
  submittedFlag: boolean = false;


  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private ajaxService: AjaxService,
    private message: MessageBarService,
    private router: Router
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
    this.flag = false;
  }

  onUpload(e) {
    e.preventDefault();
    if ($("#files").val() === "") {
      this.message.errorModal("กรุณาเลือกไฟล์อัพโหลด");
    } else {
      this.fileUpload().then(res => {
        this.arrayUpload = res;
      });
    }
  }

  fileUpload() {
    let inputFile = `<input type="file" name="files" id="files" accept=".pdf, image/*, text/plain, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
                        application/vnd.ms-excel,.doc,.docx">`;

    let f = $(".upload-panel > input")[0];
    $(".upload-panel").html(inputFile);

    let lastText = f.value.split("\\").length;
    let u = {
      name: f.value.split("\\")[lastText - 1],
      date: new Date().toLocaleDateString(),
      typePage: "RH",
      inputFile: f
    };

    this.fileArray.push(u);

    return new Promise<any>((resolve, reject) => {
      resolve(this.fileArray);
    });
  }

  onDeleteRow(index: number) {
    this.delFlag = true;
    this.fileArray.splice(index, 1);
    setTimeout(() => {
      this.delFlag = false;
    }, 300);
  }

  onSubmit() {
    this.message.comfirm(confirm => {
      if (confirm) {
        this.delFlag = true;
        this.submittedFlag = true;

        if (this.medicalWelfareForm.invalid) {
          this.message.errorModal("กรุณากรอกข้อมูลให้ครบ");
          return;
        }

        var url = "ia/int061102/save";
        let promise = new Promise<any>((resolve, reject) => {
          this.ajaxService.post(url, this.medicalWelfareForm.value, res => {
            resolve(res.json());
          }),
            error => {
              reject(this.message.errorModal("ไม่สามารถทำการบันทึกได้"));
            };
        });

        promise.then(res => {
          console.log(this.arrayUpload);
          console.log("response: ", res);
          if (Utils.isNotNull(res.id)) {
            const formData = new FormData();
            formData.append("id", res.id);

            this.fileArray.forEach(file => {
              formData.append("files", file.inputFile.files[0]);
            });

            var urlUpload = "ia/int061102/upload";
            console.log("formData: ", formData);
            this.ajaxService.upload(urlUpload, formData, res => {
              this.message.successModal(res.json().messageTh);
              this.router.navigate(["/int06/11"]);
            }),
              error => {
                this.message.errorModal("ไม่สามารถทำการบันทึกได้");
              };
          }
        });

      }
    }, "ยืนยันการบันทึกข้อมูล");

  }
}
