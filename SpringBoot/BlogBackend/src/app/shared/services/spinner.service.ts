import { Injectable } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";

@Injectable({
    providedIn: "root"
})
export class SpinnerService {

    constructor(
        private spinner: NgxSpinnerService
    ) { }

    show() {
        this.spinner.show(undefined,
            {
                type: 'ball-triangle-path',
                size: 'medium',
                bdColor: 'rgba(0, 0, 0, 0.8)',
                color: '#fff',
                fullScreen: true
            });
    }

    hide() {
        this.spinner.hide();
    }
}
