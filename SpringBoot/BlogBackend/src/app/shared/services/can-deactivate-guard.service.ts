import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs/Observable";

export interface CanComponentDeactivate {
    canDactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate>{

    /**
    * 當使用者要離開這個 Guard 所防守的路由時，會觸發這個函式
    *
    * @param {LoginComponent} component - 該路由的 Component
    * @param {ActivatedRouteSnapshot} currentRoute - 當前的路由
    * @param {RouterStateSnapshot} currentState - 當前路由狀態的快照
    * @param {RouterStateSnapshot} [nextState] - 欲前往路由的路由狀態的快照
    * @returns {(boolean | Observable<boolean> | Promise<boolean>)}
    */
    canDeactivate(component: CanComponentDeactivate,
        currentRoute: ActivatedRouteSnapshot,
        cuurentState: RouterStateSnapshot,
        nextState?: RouterStateSnapshot): Observable<boolean> | Promise<boolean> |
        boolean {
        return component.canDactivate();
    }
}