package com.vitalik123.movieappotus.app

import com.vitalik123.core_api.ApplicationCoreFacade
import com.vitalik123.database.di.ApplicationRoomFacade
import com.vitalik123.network.di.ApplicationNetworkFacade

interface AppFacade : ApplicationCoreFacade, ApplicationNetworkFacade, ApplicationRoomFacade