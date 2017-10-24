import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { AjaxService } from '../../../common/services/ajax.service';
import { MessageBarService } from '../../../common/services/message-bar.service';

import { UserManagement } from '../../../common/models/userManagement';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-userManagement-detail',
    templateUrl: 'userManagement-detail.html'
})
export class UserManagementDetailPage implements OnInit {

    private title: string;
    private statusPage: string;
    static ADD: string = "ADD";
    static UPDATE: string = "UPDATE";
    private userManagementUpdate: UserManagement;
    private passwordRule: any;
    private userRule: any;
    private $form: any;

    private isRequired: string = "";
    private typePassword: string;

    constructor(
        private ajaxService: AjaxService,
        private messageBarService: MessageBarService,
        private location: Location,
        private router: Router,
        private route: ActivatedRoute
    ) {

    }

    ngOnInit() {
        this.typePassword = "password";
        let id = this.route.snapshot.params['id'];
        this.$form = $('#userManagementForm');
        if (id) {
            this.title = 'Edit UserManagement';
            $('#username').attr('disabled', 'disabled');
            this.statusPage = UserManagementDetailPage.UPDATE;
            const getURL = `api/preferences/userManagement/${id}`;
            this.ajaxService.get(getURL, (success: Response) => {
                let body: any = success.json();
                this.userManagementUpdate = body.data as UserManagement;

                this.userManagementUpdate.password = "";
                //Set Value
                this.$form.form('set values', this.userManagementUpdate);

                this.$form.form('set values', {
                    enabled: this.userManagementUpdate.enabled == "Y" ? true : false,
                    accountNonExpired: this.userManagementUpdate.accountNonExpired == "Y" ? true : false,
                    credentialsNonExpired: this.userManagementUpdate.credentialsNonExpired == "Y" ? true : false,
                    accountNonLocked: this.userManagementUpdate.accountNonLocked == "Y" ? true : false
                });
            }, (err: Response) => {
                let body: any = err.json();
                this.messageBarService.error(body.error);
            });
        } else {
            this.title = 'Add UserManagement';
            this.statusPage = UserManagementDetailPage.ADD;
            this.isRequired = "required";

        }
    }

    ngAfterViewInit() {
        this.formInit();
    }

    formInit(): void {
        this.ruleInit();
        this.$form.form({
            on: 'blur',
            inline: true,
            fields: {
                username: this.userRule,
                password: this.passwordRule
            }
        });
        this.inputPassword();
    }

    ruleInit() {
        let EMPTY_MESSAGE = "กรุณากรอกข้อมูลในช่องว่าง.";
        this.userRule = {
            identifier: 'username',
            rules: [
                {
                    type: 'empty',
                    prompt: EMPTY_MESSAGE
                },
                {
                    type: 'regExp[/^[a-zA-Z0-9_-]{1,30}$/]',
                    prompt: "กรุณากรอกตัวอักษรพิมพ์ใหญ่อย่างน้อย 1 ตัวอักษร และตัวเลขอย่างน้อย 1 ตัวอักษร"
                }
            ]
        };

        this.passwordRule = {
            identifier: 'password',
            rules: [
                {
                    type: 'empty',
                    prompt: EMPTY_MESSAGE
                },
                {
                    type: 'maxLength[8]',
                    prompt: 'กรุณากรอกข้อมูล {ruleValue} ตัวอักษร'
                },
                {
                    type: 'minLength[8]',
                    prompt: 'กรุณากรอกข้อมูล {ruleValue} ตัวอักษร'
                },
                {
                    type: 'regExp[/^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/]',
                    prompt: "กรุณากรอกตัวอักษรพิมพ์ใหญ่อย่างน้อย 1 ตัวอักษร และตัวเลขอย่างน้อย 1 ตัวอักษร"
                }
            ]
        };
    }

    save() {
        let valid: boolean = this.$form.form('validate form');

        if (!valid) return false;

        //loading
        this.$form.addClass("loading");
        let allFields = this.$form.form('get values');
        console.log("allFields", allFields);

        const url = "api/preferences/userManagement";

        if (this.statusPage == UserManagementDetailPage.ADD) {
            let userManagementAdd = allFields as UserManagement;
            userManagementAdd = this.convertCheckboxVal(userManagementAdd);

            //check exist username
            const getURL = `api/preferences/userManagement/exist/${userManagementAdd.username}`;
            this.ajaxService.get(getURL, (success: Response) => {
                let body: any = success.json();
                let existUserManagement = body.data as UserManagement;

                if (existUserManagement.username) {
                    this.messageBarService.error('Username is existing...');
                    this.$form.removeClass("loading");
                } else {
                    this.ajaxService.post(url, userManagementAdd, (success: Response) => {
                        this.messageBarService.success("บันทึกข้อมูลสำเร็จ.");
                        this.$form.removeClass("loading");
                        this.back();
                    }, (error: Response) => {
                        let body: any = error.json();
                        this.messageBarService.error(body.error);
                        this.$form.removeClass("loading");
                    });
                }
            }, (err: Response) => {
                let body: any = err.json();
                this.messageBarService.error(body.error);
                this.$form.removeClass("loading");
            });
        } else {
            let update = $.extend(this.userManagementUpdate, allFields) as UserManagement;
            update = this.convertCheckboxVal(update);
            this.ajaxService.put(url, update, (success: Response) => {
                this.messageBarService.success("บันทึกข้อมูลสำเร็จ.");
                this.$form.removeClass("loading");
                this.back();
            }, (error: Response) => {
                let body: any = error.json();
                this.messageBarService.error(body.error);
                this.$form.removeClass("loading");
            });
        }

    }

    back(): void {
        this.router.navigate(['/userManagement']);
    }

    showHidePassword(): void {
        this.typePassword == "password" ? this.typePassword = "text" : this.typePassword = "password";
    }

    inputPassword(): void {
        if ((this.statusPage == UserManagementDetailPage.UPDATE)) {
            if (this.$form.form('get value', 'password').length == 0) {
                this.$form.form('remove rule', 'password');
            } else {
                this.$form.form('add rule', 'password', this.passwordRule);
            }
        }
    }

    convertCheckboxVal(userManagement: UserManagement): UserManagement {
        userManagement.enabled = userManagement.enabled ? 'Y' : 'N';
        userManagement.accountNonExpired = userManagement.accountNonExpired ? 'Y' : 'N';
        userManagement.credentialsNonExpired = userManagement.credentialsNonExpired ? 'Y' : 'N';
        userManagement.accountNonLocked = userManagement.accountNonLocked ? 'Y' : 'N';
        return userManagement;
    }

}