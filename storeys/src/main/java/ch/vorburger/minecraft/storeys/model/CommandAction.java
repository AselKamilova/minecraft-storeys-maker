/**
 * ch.vorburger.minecraft.storeys
 *
 * Copyright (C) 2016 - 2017 Michael Vorburger.ch <mike@vorburger.ch>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.vorburger.minecraft.storeys.model;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;

public class CommandAction implements SynchronousAction<CommandResult> {

    // TODO It would be good if there was a way to know when a /command was "done" ..
    //   to be able to make this an asynchronous Action returning a CompletionStage - but how?

    private String commandLineWithoutSlash;

    public CommandAction setCommand(String commandLine) {
        this.commandLineWithoutSlash = commandLine.trim();
        if (commandLineWithoutSlash.startsWith("/")) {
            commandLineWithoutSlash = commandLineWithoutSlash.substring(1);
        }
        return this;
    }

    @Override
    public CommandResult executeSynchronously(ActionContext context) throws ActionException {
        return Sponge.getCommandManager().process(context.getCommandSource(), commandLineWithoutSlash);
    }

}