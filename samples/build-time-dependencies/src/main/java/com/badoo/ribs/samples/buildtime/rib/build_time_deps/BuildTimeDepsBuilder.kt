package com.badoo.ribs.samples.buildtime.rib.build_time_deps

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.samples.buildtime.rib.build_time_deps.BuildTimeDepsRouter.Configuration
import com.badoo.ribs.samples.buildtime.rib.profile.ProfileBuilder

class BuildTimeDepsBuilder : SimpleBuilder<BuildTimeDeps>() {

    override fun build(buildParams: BuildParams<Nothing?>): BuildTimeDeps {
        val backStack: BackStack<Configuration> = BackStack(
            initialConfiguration = Configuration.Default,
            buildParams = buildParams
        )
        val interactor = BuildTimeDepsPresenter(
            backStack = backStack
        )
        val router = BuildTimeDepsRouter(
            buildParams = buildParams,
            routingSource = backStack,
            profileBuilder = ProfileBuilder()
        )
        return BuildTimeDepsNode(
            buildParams = buildParams,
            viewFactory = BuildTimeDepsViewImpl.Factory().invoke(null),
            plugins = listOf(interactor, router)
        )
    }
}
