package ru.otus.homework.patternAdapter.scopes;

import ru.otus.homework.patternAdapter.command.Command;

public class ClearCurrentScopeCommand implements Command {

    @Override
    public void execute() {
        InitCommand.CURRENT_SCOPES.remove();
    }
}
