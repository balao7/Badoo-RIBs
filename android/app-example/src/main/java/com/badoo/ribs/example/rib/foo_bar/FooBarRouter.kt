package com.badoo.ribs.example.rib.foo_bar

import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.example.rib.foo_bar.FooBarRouter.Configuration
import kotlinx.android.parcel.Parcelize
import com.badoo.ribs.core.BuildContext

class FooBarRouter(
    buildContext: BuildContext<Nothing?>
): Router<Configuration, Nothing, Configuration, Nothing, FooBarView>(
    buildContext = buildContext,
    initialConfiguration = Configuration.Default
) {
    sealed class Configuration : Parcelable {
        @Parcelize object Default : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction<FooBarView> =
        RoutingAction.noop()
}
