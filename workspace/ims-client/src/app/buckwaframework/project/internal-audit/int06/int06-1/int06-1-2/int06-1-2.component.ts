import { Component, OnInit, AfterViewChecked, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { Int0612Service } from 'projects/internal-audit/int06/int06-1/int06-1-2/int06-1-2.service';
import { BreadCrumb } from 'models/breadcrumb';
import { Int061Service } from 'projects/internal-audit/int06/int06-1/int06-1.service';
import { Router } from '@angular/router';
import { AuthService } from 'services/auth.service';
declare var $: any;
@Component({
  selector: 'app-int06-1-2',
  templateUrl: './int06-1-2.component.html',
  styleUrls: ['./int06-1-2.component.css'],
  providers: [Int0612Service]
})
export class Int0612Component implements OnInit, AfterViewInit {

  //value init
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
  ];
  show: boolean = true;
  loading: boolean = false;
  tableLoading: boolean = false;
  constructor(
    private int0612Service: Int0612Service,
    private int061Service: Int061Service,
    private router: Router,
    private authService: AuthService,
    private cdRef: ChangeDetectorRef
  ) {

    this.int061Service.setDataLedger(null)
    if (this.int061Service.getDataBudget() == null) {
      this.router.navigate(['/int06/1/1']);
    }
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06120');

  }
  ngAfterViewInit() {
    this.dataTable();
    this.cdRef.detectChanges();
  }

  async onChangeUpload(file: any) {
    this.loading = await true;
    await this.int0612Service.onChangeUpload(file);
    setTimeout(() => {
      this.loading = false;
    }, 300);
  }
  //upload
  async onSubmit(f: any) {

    this.tableLoading = await true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.int0612Service.onSubmit(formBody, this.int061Service).then(() => {
      this.tableLoading = false;

      if (this.int061Service.getDataLedger() == 0) {
        this.show = true;
      } else {
        this.show = false;
      }

    });
  }

  dataTable() {
    this.int0612Service.dataTable();
  }

  claer = () => {
    this.show = true;
    this.int0612Service.claer(this.int061Service);
  }
}
