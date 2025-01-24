
import { reducer } from "./toastReducer";
import { Action, State, TOAST_REMOVE_DELAY } from "./toastTypes";

export const listeners: Array<(state: State) => void> = [];

export let memoryState: State = { toasts: [] };

export let count = 0;

export function genId() {
  count = (count + 1) % Number.MAX_SAFE_INTEGER;
  return count.toString();
}

export const toastTimeouts = new Map<string, ReturnType<typeof setTimeout>>();

export function dispatch(action: Action) {
  memoryState = reducer(memoryState, action);
  listeners.forEach((listener) => {
    listener(memoryState);
  });
}

export const addToRemoveQueue = (toastId: string) => {
  if (toastTimeouts.has(toastId)) {
    return;
  }

  const timeout = setTimeout(() => {
    toastTimeouts.delete(toastId);
    dispatch({
      type: 'REMOVE_TOAST',
      toastId: toastId,
    });
  }, TOAST_REMOVE_DELAY);

  toastTimeouts.set(toastId, timeout);
};
