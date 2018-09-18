import { Component } from "@angular/core";

declare var $: any;

@Component({
    selector: 'ui-segments',
    templateUrl: './segments.html',
    styleUrls: ['./segments.css']
})
export class Segments {
    constructor() {
        // TODO
    }

    async copySegment(e: any, what: string) {
        e.target.innerHTML = await "copied";
        let $temp = await $("<textarea id='copyToClipboard'></textarea>");
        await $("body").append($temp);
        await $temp.val(this.selectSegment(what)).select();
        await document.execCommand("copy");
        await $temp.remove();
        setTimeout(() => e.target.innerHTML = "copy", 1000);
    }

    selectSegment(what: string): string {
        switch (what) {
            case '':
                return `<div class="ui segments">\r\n<div class="ui top attached header">Header</div>\r\n\t<div class="ui bottom segment">\r\n\t\t<p>Content</p>\r\n\t</div>\r\n</div>`;
            case 'loading':
                return `<div class="ui segments">\r\n<div class="ui top attached header">Header</div>\r\n\t<div class="ui bottom segment">\r\n\t\t<div class="ui active dimmer" *ngIf="true">\r\n\t\t\t<div class="ui small text loader">Loading</div>\r\n\t\t</div>\r\n\t\t<p>Content</p>\r\n\t</div>\r\n</div>`;
            case 'loading-inverted':
                return `<div class="ui segments">\r\n<div class="ui top attached header">Header</div>\r\n\t<div class="ui bottom segment">\r\n\t\t<div class="ui inverted active dimmer" *ngIf="true">\r\n\t\t\t<div class="ui small text loader">Loading</div>\r\n\t\t</div>\r\n\t\t<p>Content</p>\r\n\t</div>\r\n</div>`;
        }
    }
}